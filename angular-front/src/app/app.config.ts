import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { loadingInterceptorInterceptor } from './interceptors/loading-interceptor-interceptor';
import { provideMarkdown } from 'ngx-markdown';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(), provideMarkdown(), provideHttpClient(withInterceptors([loadingInterceptorInterceptor])),
    provideRouter(routes)
  ]
};
