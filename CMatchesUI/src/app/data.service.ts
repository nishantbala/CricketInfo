import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  public id: number;
  public matchId: number;
  public date: string;
  public dateTimeGMT: string;
  public team_1: string;
  public team_2:string;
  public type: string;
  public squad :string;
  public toss_winner_team :string;
  public winner_team :string;
  public matchStarted :string;
  public fromDashboard :boolean;
}
