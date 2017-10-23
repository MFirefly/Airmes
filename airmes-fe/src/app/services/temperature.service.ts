import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Measurement } from '../interfaces/Measurement';

@Injectable()
export class TemperatureService {

    constructor(private http: Http) { }

    getTemperature(): Promise<Measurement> {
        // Make the request
        return this.http.get('http://localhost:8080/airmes/temperature/current')
            .toPromise()
            .then(response => response.json().result as Measurement)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error(`Server returned code ${error.status}, body was: ${error.error}`);
        return Promise.reject(error.message || error);
    }
}