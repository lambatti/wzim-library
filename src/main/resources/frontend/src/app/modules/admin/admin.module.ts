import { RouterModule } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPanelComponent } from './adminPanel/adminPanel.component';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzPageHeaderModule } from 'ng-zorro-antd/page-header';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { CoreModule } from '../../core/core.module';
import { WorkersSummaryComponent } from './workersSummary/workersSummary.component';
import { AddWorkerComponent } from './addWorker/addWorker.component';
import { NzInputModule } from 'ng-zorro-antd/input';
import { ReactiveFormsModule } from '@angular/forms';
import { DeleteUserComponent } from './deleteUser/deleteUser.component';
import { NzTypographyModule } from 'ng-zorro-antd/typography';


let routing = RouterModule.forChild([
  { path: 'auth', component: AuthComponent },
  {
    path: '', component: AdminPanelComponent, children: [
      { path: 'workersSummary', component: WorkersSummaryComponent },
      { path: 'addWorker', component: AddWorkerComponent },
      { path: 'deleteUser', component: DeleteUserComponent }
      //  { path: 'borrowedBooks', component: WorkersSummaryComponent },

    ]
  },
  { path: '**', redirectTo: 'auth' }
]);

@NgModule({
  imports: [CommonModule, NzFormModule, NzLayoutModule, routing,
    NzButtonModule, NzMenuModule, NzToolTipModule, NzIconModule, NzPageHeaderModule,
    NzDescriptionsModule, NzModalModule, CoreModule, NzInputModule, ReactiveFormsModule, NzTypographyModule],
  declarations: [AuthComponent, AdminPanelComponent, WorkersSummaryComponent, AddWorkerComponent, DeleteUserComponent]
})
export class AdminModule {
}
