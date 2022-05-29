import { Injectable } from '@angular/core';
import { AdminService } from '../http/admin.service';
import { Observable } from 'rxjs';
import { UsersToPromotionModel } from '../../model/usersToPromotion.model';


@Injectable()
export class AdminRepository {

  constructor(private readonly _adminService: AdminService) {
  }

  getAllUsers(): Observable<UsersToPromotionModel[]> {
    return this._adminService.getAllWorkers();
  }


}
