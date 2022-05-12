import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NzWaveModule } from 'ng-zorro-antd/core/wave';
import { ModuleModule } from './modules/module.module';
import { CoreModule } from './core/core.module';
import { RouterModule } from '@angular/router';
import { routing } from './app.routing';
import { AuthorizationService } from './core/http/authorization.service';
import { AuthenticationService } from './core/authentication/authentication.service';
import { AuthService } from './core/authentication/auth.service';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { AuthGuard } from './core/guards/authGuard.guard';
import { BookService } from './core/http/book.service';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzWaveModule,
    CoreModule,
    ModuleModule,
    RouterModule,
    routing
  ],
  providers: [{
    provide: NZ_I18N,
    useValue: en_US
  }, AuthorizationService,
    AuthenticationService,
    AuthService,
    JwtHelperService, {
      provide: JWT_OPTIONS,
      useValue: JWT_OPTIONS
    },
    AuthGuard,
    BookService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
