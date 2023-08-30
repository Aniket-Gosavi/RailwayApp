import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatRadioModule} from '@angular/material/radio';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatBadgeModule} from '@angular/material/badge';
import {MatCardModule} from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { authInterceptorProviders } from './helpers/auth.interceptor';

import { FrontPageComponent } from './front-page/front-page.component';

import { FilterPipe } from './shared/filter.pipe';
import { AddtrainsComponent } from './addtrains/addtrains.component';
import { ShowTrainsComponent } from './show-trains/show-trains.component';
import { SearchtrainComponent } from './searchtrain/searchtrain.component';
import { UpdatetrainComponent } from './updatetrain/updatetrain.component';
import { BookTrainComponent } from './book-train/book-train.component';
import { BookreceiptComponent } from './bookreceipt/bookreceipt.component';
import { ShowbookingsComponent } from './showbookings/showbookings.component';
import { AboutUsComponent } from './about-us/about-us.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    FrontPageComponent,
    FilterPipe,
    AddtrainsComponent,
    ShowTrainsComponent,
    SearchtrainComponent,
    UpdatetrainComponent,
    BookTrainComponent,
    BookreceiptComponent,
    ShowbookingsComponent,
    AboutUsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatIconModule,
    MatDialogModule,
    MatGridListModule,
    MatRadioModule,
    MatSidenavModule,
    MatListModule,
    MatBadgeModule,
    MatCardModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
