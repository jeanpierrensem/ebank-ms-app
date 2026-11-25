import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AsyncPipe } from '@angular/common';
import { account, accountListState, RequestStatus } from '../../model/models';
import { catchError, map, Observable, of } from 'rxjs';
import { LoadingService } from '../services/loading';



@Component({
  imports: [AsyncPipe],
  selector: 'app-accounts',
  styleUrl: './accounts.css',
  templateUrl: './accounts.html',
})
export class Accounts {
  private httpClient = inject(HttpClient);
  public loadingService  = inject(LoadingService)
  accounts$: Observable<accountListState> = this
    .httpClient.get<account[]>('http://localhost:9999/EBANK-SERVICE/accounts')
    .pipe(
      map(resp=> {
        return { accounts : resp, status : RequestStatus.SUCCESS}
      }),
      catchError(err => {
        return of({ status: RequestStatus.ERROR, errorMessage : err.statusText})

      })
    )
  protected readonly RequestStatus = RequestStatus;
}
