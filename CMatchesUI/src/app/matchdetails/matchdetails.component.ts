import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../data.service';
import {MatchService} from '../match.service';
import {UserService} from '../user.service';
import {MatchDetailsService} from '../matchdetails.service';
@Component({
  selector: 'app-matchdetails',
  templateUrl: './matchdetails.component.html',
  styleUrls: ['./matchdetails.component.css']
})
export class MatchDetailsComponent implements OnInit {

  match: any = {};
  fromDashboard: boolean;
  id: any;

  constructor(
      private router: Router,
      private dataService: DataService,
      private matchService: MatchService,
      private userService: UserService,
      private matchDetailsService: MatchDetailsService
  ) {
  }

      // format published Date
  formatDate(dateString: any): any {
    var d = new Date(dateString).toDateString();  
    return d;
  }

  ngOnInit() {
      /** Fetching news Id from dashboard page and checking whether it is create or edit request */
      this.match = this.matchDetailsService;
      if (this.userService.userId == undefined) {
                      this.router.navigate(['./login']);
        }
  }

  redirect() {
      this.router.navigate(['favlist']);
    }
}

