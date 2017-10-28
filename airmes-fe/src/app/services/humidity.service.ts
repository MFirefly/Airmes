import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Measurement } from '../interfaces/Measurement';

@Injectable()
export class HumidityService {

    constructor(private http: Http) { }

    /**
     * Retrieves current humidity from server
     */
    getHumidity(): Promise<Measurement> {
        // Make the request
        return this.http.get('http://localhost:1410/airmes/humidity/current')
            .toPromise()
            .then(response => response.json().result as Measurement)
            .catch(this.handleError);
    }

    /**
     * Retrieves humidity history from server
     */
    getHumidityHistory(): Promise<Measurement[]> {
        return this.http.get('http://localhost:1410/airmes/humidity/history')
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
