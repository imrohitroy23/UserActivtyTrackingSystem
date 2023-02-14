import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserActivity } from '../components/user-activity';

@Injectable({
  providedIn: 'root'
})
export class UserActivityService {

  private readonly baseUrl = 'http://localhost:8084/user/user-logs';

  constructor(private http: HttpClient) { }

  getAllUserActivity(): Observable<UserActivity[]> {
    return this.http.get<UserActivity[]>(`${this.baseUrl}`);
  }
}
