/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { TransactionSumDetails } from '../../models/transaction-sum-details';

export interface FindTransactionByDate$Params {
  'user-id': number;
  'start-date': string;
  'end-date': string;
}

export function findTransactionByDate(http: HttpClient, rootUrl: string, params: FindTransactionByDate$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<TransactionSumDetails>>> {
  const rb = new RequestBuilder(rootUrl, findTransactionByDate.PATH, 'get');
  if (params) {
    rb.path('user-id', params['user-id'], {});
    rb.query('start-date', params['start-date'], {});
    rb.query('end-date', params['end-date'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<TransactionSumDetails>>;
    })
  );
}

findTransactionByDate.PATH = '/statistics/sum-by-date/{user-id}';
