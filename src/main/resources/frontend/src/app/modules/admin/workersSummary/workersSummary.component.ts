import { Component, OnInit } from '@angular/core';
import { UsersToPromotionModel } from '../../../model/usersToPromotion.model';
import { AdminRepository } from '../../../core/services/admin.repository';


@Component({
  templateUrl: 'workersSummary.component.html',
  styleUrls: ['workersSummary.component.scss']
})
export class WorkersSummaryComponent implements OnInit {
  isVisible: boolean = false;


  constructor(private readonly _adminRepository: AdminRepository) {
  }

  ngOnInit() {
    this._adminRepository.getAllUsers().subscribe(item => {
      this.dataWorkers = item;
    })
  }

  // TODO to tylko statyczne dane później do zmiany

  dataWorkers: UsersToPromotionModel[] = [];

  // dataWorkers: any = [
  //   {
  //     id: 1,
  //     firstName: 'Tomasz',
  //     lastName: 'Comasz',
  //     gender: 'Mężczyzna',
  //     employmentDate: '10-10-2017'
  //   },
  //   {
  //     id: 2,
  //     firstName: 'Tomasz',
  //     lastName: 'Comasz',
  //     gender: 'Mężczyzna',
  //     employmentDate: '10-10-2017'
  //   },
  //   {
  //     id: 3,
  //     firstName: 'Tomasz',
  //     lastName: 'Comasz',
  //     gender: 'Mężczyzna',
  //     employmentDate: '10-10-2017'
  //   },
  //   {
  //     id: 4,
  //     firstName: 'Tomasz',
  //     lastName: 'Comasz',
  //     gender: 'Mężczyzna',
  //     employmentDate: '10-10-2017'
  //   },
  //   {
  //     id: 5,
  //     firstName: 'Tomasz',
  //     lastName: 'Comasz',
  //     gender: 'Mężczyzna',
  //     employmentDate: '10-10-2017'
  //   }
  // ];
  showModal(id: number): void {
    console.log(id);
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

}
