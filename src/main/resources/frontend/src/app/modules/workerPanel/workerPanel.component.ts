import { Component, OnInit } from '@angular/core';
import { WorkerRepository } from '../../core/services/worker.repository';
import { BorrowActionRequest } from '../../model/borrowOrProlongationActions.model';


@Component({
  selector: 'app-worker-panel',
  templateUrl: 'workerPanel.component.html'
})
export class WorkerPanelComponent implements OnInit{
  borrowedBooks: BorrowActionRequest[] = [];
  prolongationBooks: any = [];
  constructor(private readonly _workerRepository: WorkerRepository) {
  }


  ngOnInit(): void {
    this._workerRepository.getAllBorrowRequest().subscribe(item => {
      this.borrowedBooks = item;
    })
    this._workerRepository.getAllProlongationRequest().subscribe(item => {
      if (item.length !== 0) {
        this.prolongationBooks = item;
      }
    })
  }
  accept(isBorrow: boolean, email: string, bookSlug: string) {
    if (isBorrow) {
      this._workerRepository.acceptBorrow(email,bookSlug).subscribe();
    } else {
      this._workerRepository.acceptProlongation(email, bookSlug).subscribe()
    }
    window.location.reload();
  }

  reject(isBorrow: boolean, email: string, bookSlug: string) {
    if (isBorrow) {
      this._workerRepository.rejectBorrow(email,bookSlug).subscribe();
    } else {
      this._workerRepository.rejectProlongation(email, bookSlug).subscribe()
    }
    window.location.reload();

  }
}
