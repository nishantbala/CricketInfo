import { Component, OnInit } from '@angular/core';
import { MatchService } from '../match.service';
import { MATCH } from '../match';
import { MATCHDETAILS } from '../matchdetails';
import {UserService} from '../user.service';
import {DataService} from '../data.service';
import {MatchDetailsService} from '../matchdetails.service';
import {Router} from '@angular/router';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import { CalendarView, CalendarEvent, CalendarEventTimesChangedEvent } from 'angular-calendar';
import { Subject } from 'rxjs';
import { isSameMonth, isSameDay, startOfDay, endOfDay } from 'date-fns';


const colors: any = {
    red: {
      primary: '#ad2121',
      secondary: '#FAE3E3'
    },
    blue: {
      primary: '#1e90ff',
      secondary: '#D1E8FF'
    },
    yellow: {
      primary: '#e3bc08',
      secondary: '#FDF1BA'
    }
  };

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [
    MatchService
]
})
export class DashboardComponent implements OnInit {

  //calendar
  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  CalendarView = CalendarView;
  events: CalendarEvent[] = [];
  refresh: Subject<any> = new Subject();
  activeDayIsOpen: boolean = true;
  //calendar

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

    filteredMatch: MATCH[];
    match: MATCH[] = [];
    matchDetails: MATCHDETAILS;
    favMatchId: number;
    disableButton = false;
    userId: string;
    isAdmin: boolean;


    constructor(
        private router: Router,
        private matchService: MatchService,
        private userService: UserService,
        private dataService: DataService,
        private matchDetailsService: MatchDetailsService) {

    }

    performFilter(filterBy: string): MATCH[] {
      filterBy = filterBy.toLocaleLowerCase();
      return this.match.filter((match: MATCH) =>
          match.team_1.concat(match.team_2).toLocaleLowerCase().indexOf(filterBy) !== -1);
  }


    // Add To watchList / Remove from watchList
    watchMatch(event, index, selectedMatch): void {
        let text = event.target.outerText;
        this.disableButton = true;
        const currVal = (text == 'Add To Watchlist') ? 'Remove From Watchlist' : 'Add To Watchlist';
        this.favMatchId = selectedMatch.matchId;
        this.matchService.addToFavMatches(this.favMatchId).subscribe(
            data => {
                event.target.innerHTML = currVal;
            }
        );
    }

        // To get Match Details
    getMatchDetails(index): void {
          this.favMatchId = this.filteredMatch[index].matchId;
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
          this.matchDetailsService.dateTimeGMT = this.filteredMatch[index].dateTimeGMT;
          this.matchDetailsService.type = this.filteredMatch[index].type;
          this.matchDetailsService.squad = this.filteredMatch[index].squad;
          this.matchDetailsService.toss_winner_team = this.filteredMatch[index].toss_winner_team;
          this.matchDetailsService.winner_team = this.filteredMatch[index].winner_team;
          this.matchDetailsService.matchStarted = this.filteredMatch[index].matchStarted;
          this.router.navigate(['./matchdetails']);
      }

    // changing HTML
    watchMatchOK(matchId: any): any {
        var buttonText = 'Add To Watchlist';
        for (var i = 0; i < this.AlreadyRecommended.length; i++) {
            if (this.AlreadyRecommended[i].matchId == matchId) {
                buttonText = 'Remove From Watchlist';
            }
        }

        return buttonText;
    }

    // format published Date
    formatDate(dateString: any): any {
      var d = new Date(dateString).toDateString();  
      return d;
    }

    redirect() {
        this.router.navigate(['./admin']);
    }

  

    ngOnInit(): void {
       
        if (this.userService.userId == undefined) {
                      this.router.navigate(['./login']);
        }
        
        this.matchService.getAllMatches()
            .subscribe(match => {
                    this.match = match;
                    this.filteredMatch = this.match;
                    this.userId = this.userService.userId;
                    this.isAdmin = this.userService.isAdmin;
                    if (this.userId == undefined) {
                        this.router.navigate(['./login']);
                    }
                    this.addMatchesToCalendar(this.match);

                },
                error => this.errorMessage = <any>error);


        this.matchService.getAllWishListMatches()
            .subscribe(match => {
                    this.AlreadyRecommended = match;
                },
                error => this.errorMessage = <any>error);

    }

    addMatchesToCalendar(matches: MATCH[]){
        matches.forEach(match => {
            this.addEvent(match.id.toString(), match.team_1 + ' v ' + match.team_2, new Date(match.date), new Date(match.date));
        });
    }

    //Calendar
    dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
        if (isSameMonth(date, this.viewDate)) {
          if (
            (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
            events.length === 0
          ) {
            this.activeDayIsOpen = false;
          } else {
            this.activeDayIsOpen = true;
          }
          this.viewDate = date;
        }
      }

      addEvent(id: string, title: string, startDate: Date, endDate: Date): void {
        this.events = [
          ...this.events,
          {   
            id: id,         
            title: title,
            start: startOfDay(startDate),
            end: endOfDay(endDate),
            color: colors.red,
            draggable: true,
            resizable: {
              beforeStart: true,
              afterEnd: true
            }
          }
        ];
      }
    
      eventTimesChanged({
        event,
        newStart,
        newEnd
      }: CalendarEventTimesChangedEvent): void {
        this.events = this.events.map(iEvent => {
          if (iEvent === event) {
            return {
              ...event,
              start: newStart,
              end: newEnd
            };
          }
          return iEvent;
        });
        this.handleEvent('Dropped or resized', event);
      }
    
      handleEvent(action: string, event: CalendarEvent): void {
        this.getMatchDetails(Number(event.id) -1);
      }
      //Calendar

}
