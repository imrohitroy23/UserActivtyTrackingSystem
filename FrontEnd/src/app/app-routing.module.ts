import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { UserActivityComponent } from './components/user-activity/user-activity.component';

const routes: Routes = [{
  path:'',
  component:LoginComponent
},
{
  path:'activity',
  component:UserActivityComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
