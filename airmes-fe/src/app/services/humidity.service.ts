import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Measurement } from '../interfaces/Measurement';

@Injectable()
export class HumidityService {

    currentHumidity: Measurement;

    constructor(private http: Http) { }

    getHumidity(): Promise<Measurement> {
        // Make the request
        return this.http.get('http://localhost:8080/airmes/humidity/current')
            .toPromise()
            .then(response => response.json().result as Measurement)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error(`Server returned code ${error.status}, body was: ${error.error}`);
        return Promise.reject(error.message || error);
    }
}