import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Measurement } from '../interfaces/Measurement';

@Injectable()
export class TemperatureService {

    constructor(private http: Http) { }

    /**
     * Retrieves current temperature from server
     */
    getTemperature(): Promise<Measurement> {
        // Make the request
        return this.http.get('http://localhost:1410/airmes/temperature/current')
            .toPromise()
            .then(response => response.json().result as Measurement)
            .catch(this.handleError);
    }

    /**
     * Retrieves temperature history from server
     */
    getTemperatureHistory(): Promise<Measurement[]> {
        return this.http.get('http://localhost:1410/airmes/temperature/history')
        .toPromise()
        .then(response => response.json().result as Measurement[])
        .catch(this.handleError);
    }

    /**
     * Error handles
     * @param error Http error
     */
    private handleError(error: any): Promise<any> {
        console.error(`Server returned code ${error.status}, body was: ${error.error}`);
        return Promise.reject(error.message || error);
    }
}
