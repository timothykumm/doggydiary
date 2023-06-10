import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/navbar/login/login.component';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { CookieExpirationInterceptor } from './services/utils/interceptor/cookie-expiration.interceptor';
import { HomeModule } from './components/home/home.module';
import { DocumentModule } from './components/document/document.module';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HomeModule,
    DocumentModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CookieExpirationInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
