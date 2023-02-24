import { DataState } from "../enum/data-state.enum";

export interface AppState<T>{
    dataState: DataState; // LOADING, LOADED, ERROR
    appData?: T;
    error?: string;
    // NOTE: either get data **OR** error
    // ...that's why they're both optional :)
}