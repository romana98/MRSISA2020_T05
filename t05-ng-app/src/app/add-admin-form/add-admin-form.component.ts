import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-add-admin-form',
    templateUrl: './add-admin-form.component.html',
    styleUrls: ['./add-admin-form.component.css']
})
export class AddAdminFromComponent implements OnInit{

    model: adminModel = {
        name: '',
        surname: '',
        username: '',
        password: '',
        clinic: ''
    }
    clinics: any;

    constructor(private http: HttpClient){

    }


    ngOnInit(): void{
        this.clinics = this.http.get("http://localhost:8081/clinic/getClinics");

    }

    AddAdminClinic(): void{
        let url = "http://localhost:8081/admin/addAdmin"
        this.http.post(url, this.model).subscribe(
            res => {
                alert("Admin added successfully");
                location.reload();
            },
            err => {
                alert("Error has occured while adding admin");
                console.log(err);
            } 
        );
    
    }
}

export interface adminModel
{
    name: string | RegExp;
    surname: string | RegExp;
    username: string;
    password: string;
    clinic: string | RegExp;
}