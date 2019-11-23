import { Component, OnInit } from '@angular/core';
import {MATCH} from '../match';
import { MATCHDETAILS } from '../matchdetails';
import {UserService} from '../user.service';
import {MatchService} from '../match.service';
import {MatchDetailsService} from '../matchdetails.service';
import {Router} from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-favlist',
  templateUrl: './favlist.component.html',
  styleUrls: ['./favlist.component.css']
})
export class FavlistComponent implements OnInit {
  pageTitle: string = 'Match List';
  userName = this.userService.userId;
  imageWidth: number = 80;
  imageMargin: number = 2;
  errorMessage: string;
  AlreadyRecommended: MATCH[] = [];
  _listFilter: string;
  get listFilter(): string {
      return this._listFilter;
  }

  set listFilter(value: string) {
      this._listFilter = value;
      this.filteredMatch = this.listFilter ? this.performFilter(this.listFilter) : this.match;
  }

  filteredMatch: MATCH[] = new Array();
  filteredOddMatch: MATCH[] = new Array();
  filteredEvenMatch: MATCH[] = new Array();
  match: MATCH[] = [];
  matchEven: MATCH[] = [];
  matchOdd: MATCH[] = [];
  favMatchId: string;
  disableButton = false;


  constructor(
      private router: Router,
      private matchService: MatchService,
      private userService: UserService,
      private dataService: DataService,
      private matchDetailsService: MatchDetailsService) {

  }


    // changing HTML
    watchMatchOK(matchId: any): any {
        var buttonText = 'Remove From Watchlist';
        for (var i = 0; i < this.AlreadyRecommended.length; i++) {
            if (this.AlreadyRecommended[i].matchId == matchId) {
                buttonText = 'Added To Watchlist';
            }
        }

        return buttonText;
    }


    async saveToFav(favMatchID: string, itemIndex: number){
        try
        {
            var r = await this.matchService.addToFavMatchesAsync(favMatchID);
            this.filteredMatch.splice(itemIndex, 1);
        }
        catch(ex)
        {
            
        }
       
   
    }
 
    watchAndRefreshPage(event, index, selectedMatch): void {

        //remove from db
        this.saveToFav(selectedMatch.matchId, index);
        //this.watchMatch(event, index, selectedMatch);
        //remove from ui
        //this.filteredMatch.splice(index, 1);
               
    }
  performFilter(filterBy: string): MATCH[] {
      filterBy = filterBy.toLocaleLowerCase();
      return this.match.filter((match: MATCH) =>
          match.team_1.concat(match.team_2).toLocaleLowerCase().indexOf(filterBy) !== -1);
  }


        // To get Match Details
        getMatchDetails(index): void {
            this.favMatchId = String(this.filteredMatch[index].matchId);
            this.matchService.getMatchDetails(this.favMatchId).subscribe(
              matchDetails => {
                  this.matchDetailsService.matchId = matchDetails.matchId;
                  this.matchDetailsService.stat = matchDetails.stat;
                  this.matchDetailsService.score = matchDetails.score;
                  this.matchDetailsService.description = matchDetails.description;
                  this.matchDetailsService.matchStarted = matchDetails.matchStarted;
                  this.matchDetailsService.team1 = matchDetails.team1;
                  this.matchDetailsService.team2 = matchDetails.team2;
                  this.matchDetailsService.v = matchDetails.v;
                  this.matchDetailsService.ttl = matchDetails.ttl;
                  this.matchDetailsService.url = matchDetails.url;
                  this.matchDetailsService.source = matchDetails.source;
                }
            );
            this.matchDetailsService.date = new Date(this.filteredMatch[index].date).toDateString();
            this.matchDetailsService.dateTimeGMT = new Date(this.filteredMatch[index].dateTimeGMT).toDateString();
            this.matchDetailsService.type = this.filteredMatch[index].type;
            this.matchDetailsService.squad = this.filteredMatch[index].squad;
            this.matchDetailsService.toss_winner_team = this.filteredMatch[index].toss_winner_team;
            this.matchDetailsService.winner_team = this.filteredMatch[index].winner_team;
            this.matchDetailsService.matchStarted = this.filteredMatch[index].matchStarted;
            this.router.navigate(['./matchdetails']);
        }

  // Get all wish list news service
  ngOnInit(): void {
      
      this.matchService.getAllWishListMatches()
          .subscribe(match => {
                  this.match = match;
                  this.filteredMatch.splice(0, this.filteredMatch.length);
                  this.match.forEach(element => {
                    this.filteredMatch.push(element);
                  });
                  if (this.userService.userId == undefined) {
                      this.router.navigate(['./login']);
                  }
                  for (var i=0;i<this.filteredMatch.length;i++){
                    if ((i+2)%2==0) {
                        console.log(this.filteredMatch[i].team_1);
                        this.filteredEvenMatch.push(this.filteredMatch[i]);
                    }
                    else {
                        this.filteredOddMatch.push(this.filteredMatch[i]);
                    }
                }
              },
              error => this.errorMessage = <any>error);
        

  }

}
