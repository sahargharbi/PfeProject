import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideClientHydration} from '@angular/platform-browser';
import {FirstService} from "./services/first-service/first.service";
import {HTTP_INTERCEPTORS, provideHttpClient, withFetch} from "@angular/common/http";
import {HttpInterceptorService} from "./services/http-interceptor/http-interceptor.service";
import {DatepickerModule} from "ng2-datepicker";
import {DatePipe} from "@angular/common";


export const appConfig: ApplicationConfig = {

  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    },
    provideHttpClient(withFetch()), provideRouter(routes), provideClientHydration(),DatePipe],

};
