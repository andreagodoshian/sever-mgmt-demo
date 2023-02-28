import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, map, startWith } from 'rxjs/operators';
import { DataState } from './enum/data-state.enum';
import { Status } from './enum/status.enum';
import { AppState } from './interface/app-state';
import { CustomResponse } from './interface/custom-response';
import { ServerService } from './service/server.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})

export class AppComponent implements OnInit {
  appState$: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  readonly Status = Status;
  private filterSubject = new BehaviorSubject<string>("");
  filterStatus$ = this.filterSubject.asObservable();
  

  // part of JS, executed when instantiated
  // like Java, usually just for injecting dependencies
  constructor(private serverService: ServerService) {}

  // compilation error if remove ngOnInit (since "implements")
  // "Lifecycle Hook" - starts after constructor - business logic goes here
  ngOnInit(): void {
    this.appState$ = this.serverService.servers$.pipe
    (
      map((response) => {
        return {
          dataState: DataState.LOADED_STATE,
          appData: response,
        };
      }), // ^^^custom method
      startWith({ dataState: DataState.LOADING_STATE }),
      // ^^^method built-in to RxJS
      catchError((e:string) => {
        return of({ dataState: DataState.ERROR_STATE, error: e })
      }) // ^^^method built-in to RxJS
    );
  }
}
