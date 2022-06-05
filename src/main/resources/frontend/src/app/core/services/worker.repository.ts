import { WorkerService } from '../http/worker.service';
import { Observable, throwError } from 'rxjs';
import { BorrowActionRequest } from '../../model/borrowOrProlongationActions.model';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';

@Injectable()
export class WorkerRepository {
  constructor(private readonly _workerService: WorkerService) {
  }



  getAllBorrowRequest(): Observable<BorrowActionRequest[]> {
    return this._workerService.borrowRequest()
      .pipe(catchError(() => throwError(``)));
  }
  acceptBorrow(email: string, bookSlug: string): Observable<Object> {
    return this._workerService.acceptBorrowRequest(email,bookSlug)
      .pipe(catchError(() => throwError(``)))
  }
  rejectBorrow(email: string, bookSlug: string): Observable<Object> {
    return this._workerService.rejectBorrowRequest(email,bookSlug)
      .pipe(catchError(() => throwError(``)))
  }

  getAllProlongationRequest(): Observable<any> {
    return this._workerService.prolongationRequest()
      .pipe(catchError(() => throwError(``)))
  }
  acceptProlongation(email: string, bookSlug: string): Observable<Object> {
   return this._workerService.acceptProlongationRequest(email,bookSlug)
      .pipe(catchError(() => throwError(``)))
  }
  rejectProlongation(email: string, bookSlug: string): Observable<Object> {
    return this._workerService.rejectProlongationRequest(email,bookSlug)
      .pipe(catchError(() => throwError(``)))
  }

}
