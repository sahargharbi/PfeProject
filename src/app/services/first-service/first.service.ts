import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from "@angular/common/http";
import {filter, map, Observable} from "rxjs";
import {TransactionDto} from "./transaction-dto";

@Injectable()
export class FirstService {
  rootUrl = 'http://localhost:9090';

  constructor(
    private httpClient : HttpClient
  ) { }

  findAllTransactions(): Observable<any> {
    let _headers: HttpHeaders = new HttpHeaders();
    _headers = _headers.set('Authorization', 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWhhckBnbWFpbC5jb20iLCJmdWxsTmFtZSI6InNhaGFyIHNhaGFyIiwiZXhwIjoxNzI3ODE1MTM3LCJ1c2VySWQiOjcwMiwiaWF0IjoxNzI3MDk1MTM3LCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.WGoV5t8s_Z3WC1FyVSZEUuwXKYIUjub6DyeVvyEvVdI');
    const request = new HttpRequest<any>(
      'GET',
      this.rootUrl + '/transactions/',
      {
        headers: _headers,
        params: null,
        responseType: 'json'
      }
    );
    return this.httpClient.request(request)
      .pipe(
        filter(r => r instanceof HttpResponse),
      map(res => res as any)
      );
  }

  findAllTransactionsV2(): Observable<TransactionDto[]> {
    let _headers: HttpHeaders = new HttpHeaders();
    _headers = _headers.set('Authorization', 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbWFydHlAZ21haWwuY29tIiwiZnVsbE5hbWUiOiJzbWFydHkgc21hcnR5IiwiZXhwIjoxNzI3MTE4ODMzLCJ1c2VySWQiOjQ1MywiaWF0IjoxNzI2Mzk4ODMzLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.S7jmH2zUG81t892tEjBjzHvh5Y3oG4cR-RrojwBg8Xg');
    return this.httpClient.get(
      this.rootUrl + '/transactions/',
      {
        headers: _headers
      }
    ).pipe(
      filter(r => r instanceof HttpResponse),
      map(res => (res as HttpResponse<TransactionDto>).body as Array<TransactionDto>)
    )
  }
}
