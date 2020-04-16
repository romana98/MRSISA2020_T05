import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-register-patient-form',
    templateUrl: './register-patient.component.html',
    styleUrls: ['./register-patient.component.css']
})
export class RegisterPatientForm implements OnInit{

    model: patientModel = {
        email : '',
        password : '',
        name : '',
        surname : '',
        address : '',
        city : '',
        country : '',
        phone_number : '',
        insurance_number : ''
    }

    constructor(private http: HttpClient){

    }

    ngOnInit(): void{

    }

    registerPatient(): void{
        let url = "http://localhost:8081/patients/registerPatient"
        this.http.post(url,this.model).subscribe(
            res => {
                alert("Your registration request has been sent! Your activation link will be sent to you soon.");
                location.reload();
            },
            err => {
                alert("Error has occured while registering your profile!");
                console.log(err)
            }
        );
    }
}
    export interface patientModel{
        email : string;
        password : string;
        name : string;
        surname : string;
        address : string;
        city : string;
        country : string;
        phone_number : string;
        insurance_number : string;
    }

