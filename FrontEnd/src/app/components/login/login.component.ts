import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { HttpHeaders } from '@angular/common/http';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user: any = {
    username: 'a',
    password: 'ad',
    browserName: 'Ad',
    osName: 'AD',
    ipAddress: 'a',
  };

  constructor(private http: HttpClient, private _router: Router) {}

  ngOnInit(): void {
    console.log(window.navigator.userAgent);
    this.user.browserName = this.getBrowserName();
    this.user.osName = this.getOsName();
    this.getIPAddress();


  }

  ip: any;
  public getIPAddress() {
    return this.http
      .get('http://api.ipify.org/?format=json')
      .subscribe((data: any) => {
        this.user.ipAddress= JSON.stringify(data.ip);
        console.log(this.ip);
      });
  }
  login() {
    this.http
      .post<any>('http://localhost:8084/user/login', this.user)
      .subscribe((user) => {
        localStorage.setItem('id', user.id);
        localStorage.setItem('loginTime', user.loginTime);
        localStorage.setItem('ipAddress',user.ipAddress)

        this._router.navigate(['/activity']);
        Swal.fire('success',"login successful",'success')

      },(error)=>{
        Swal.fire('error',"Something went wrong",'error')
      });
  }

  private getBrowserName() {
    const userAgent = window.navigator.userAgent;
    const browsers: any = {
      chrome: /Chrome/i,
      safari: /safari/i,
      firefox: /firefox/i,
      ie: /Edg/i,
    };

    for (const key in browsers) {
      if (browsers[key].test(userAgent)) {
        return key;
      }
    }
    return 'unknown';
  }

  private getOsName() {
    const userAgent = window.navigator.userAgent;
    const os: any = {
      windows: /Windows Nt/i,
      mac: /macintosh/i,
      linux: /linux/i,
      android: /android/i,
      ios: /ipad|iphone|ipod/i,
    };

    for (const key in os) {
      if (os[key].test(userAgent)) {
        return key;
      }
    }
    return 'unknown';
  }
}
