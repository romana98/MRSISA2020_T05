import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ActivatedRoute, Router} from '@angular/router';



@Component({
  selector: 'app-nurse-authenticate',
  templateUrl: './nurse-authenticate.component.html',
  styleUrls: ['./nurse-authenticate.component.css']
})
export class NurseAuthenticateComponent implements OnInit {

  displayedColumns: string[] = ['name', 'description', 'authenticate'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private router: ActivatedRoute, private http: HttpClient,
              public dialog: MatDialog, private r:Router) {
  }

  ngOnInit(): void {
    let params = new HttpParams();
    params = params.append('apt_id', this.router.snapshot.queryParamMap.get('apt_id').toString())
    this.http.get("http://localhost:8081/nurse/getUnauthenticatedMedicines",{params:params})
      .subscribe((res) => {
          console.log(res);
          // @ts-ignore
          this.dataSource.data = res;
        }, (err) => {
          this._snackBar.open("Something went wrong!", "Close", {
            duration: 2000,
          });
        }
      );

    this.dataSource.paginator = this.paginator;
  }

  authenticate(element: any) {

    let url = "http://localhost:8081/nurse/authenticateReceipt";
    //let params = new HttpParams().set("apt_medic_id", element.id);
    let apt_medic_id = element.id;
    this.http.post(url, apt_medic_id)
      .subscribe((res) => {
        this._snackBar.open("Authentification successful!", "Close", {
          duration: 2000,
        });
        this.refreshData();
        }, (err) => {
          this._snackBar.open("Something went wrong!", "Close", {
            duration: 2000,
          });
        }
      );
  }

  refreshData(){
    let params = new HttpParams();
    params = params.append('apt_id', this.router.snapshot.queryParamMap.get('apt_id').toString())
    this.http.get("http://localhost:8081/nurse/getUnauthenticatedMedicines",{params:params})
      .subscribe((res) => {
          console.log(res);
          // @ts-ignore
          this.dataSource.data = res;
        }, (err) => {
          this._snackBar.open("Something went wrong!", "Close", {
            duration: 2000,
          });
        }
      );

    this.dataSource.paginator = this.paginator;
  }

}
