import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginComponent } from './login/login.component';

import { RegisterComponent } from './register/register.component';
import { AddtrainsComponent } from './addtrains/addtrains.component';
import { ShowTrainsComponent } from './show-trains/show-trains.component';
import { CancelticketComponent } from './cancelticket/cancelticket.component';
import { DeletetrainComponent } from './deletetrain/deletetrain.component';
import { SearchtrainComponent } from './searchtrain/searchtrain.component';
import { UpdatetrainComponent } from './updatetrain/updatetrain.component';
import { BookTrainComponent } from './book-train/book-train.component';
import { AuthguardService } from './services/authguard.service';
import { BookreceiptComponent } from './bookreceipt/bookreceipt.component';
import { ShowbookingsComponent } from './showbookings/showbookings.component';
import { AboutUsComponent } from './about-us/about-us.component';



const routes: Routes = [
  {path:"signup",component:RegisterComponent},
  {path:"login",component:LoginComponent},
  {path:"addTrains",component:AddtrainsComponent,canActivate: [AuthguardService]},
  {path:"",component:FrontPageComponent},
  {path:"show",component:ShowTrainsComponent,canActivate: [AuthguardService]},
  {path:"cancel",component:CancelticketComponent,canActivate: [AuthguardService]},
  {path:"delete",component:DeletetrainComponent,canActivate: [AuthguardService]},
  {path:"search",component:SearchtrainComponent,canActivate: [AuthguardService]},
  {path:"update",component:UpdatetrainComponent,canActivate: [AuthguardService]},
  {path:"book",component:BookTrainComponent,canActivate: [AuthguardService]},
  {path:"receipt",component:BookreceiptComponent,canActivate: [AuthguardService]},
  {path:"mybook",component:ShowbookingsComponent,canActivate: [AuthguardService]},
  {path:"about",component:AboutUsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
