import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MatchDetailsService {

  constructor() { }

  public matchId: number;
  public stat: string;
  public score: string;
  public description: string;
  public matchStarted: string;
  public team1: string;
  public team2: string;
  public v: string;
  public ttl: string;
  public url: string;
  public source: string;
  public pubDate: string;
  public creditsLeft: string;

  public date: string;
  public dateTimeGMT: string;
  public type: string;
  public squad: string;
  public toss_winner_team: string;
  public winner_team: string;

}
