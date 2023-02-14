import { HttpClient } from '@angular/common/http'
import { Component } from '@angular/core'
import { Router } from '@angular/router'

import { Observable } from 'rxjs'
import { UserActivityService } from 'src/app/services/user-activity.service'
import Swal from 'sweetalert2'
import { UserActivity } from '../user-activity'

@Component({
  selector: 'app-user-activity',
  templateUrl: './user-activity.component.html',
  styleUrls: ['./user-activity.component.css'],
})
export class UserActivityComponent {
  userActivity: UserActivity[] | undefined

  constructor(
    private http: HttpClient,
    private _router: Router,
    private userActivityService: UserActivityService,
  ) {}

  ngOnInit() {
    this.userActivityService.getAllUserActivity().subscribe((data) => {
      this.userActivity = data
    })
  }
  logout() {
    const id = localStorage.getItem('id')
    console.log(id)

    this.http
      .post<any>('http://localhost:8084/user/logout', { id })
      .subscribe(() => {
        localStorage.removeItem('id')
        Swal.fire('success', 'logged out', 'success')

        this._router.navigate(['/'])
      },(error)=>{
        Swal.fire('error',"Something went wrong",'error')
      })
  }
}
