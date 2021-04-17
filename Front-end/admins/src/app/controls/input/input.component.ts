import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {
  @Input() label: string;
  @Input() placeholder: string;
  @Input() type = 'text';
  @Input() name: string;
  @Input() mutilpleRow = false;
  @Input() rows: number;
  @Input() col = 12;
  ngClass = {};
  @Input() disabled: string;
  @Input() formControl: FormControl;
  @Input() hidden: string;
  modelData: string;

  // start model
  @Output() NgModuleChange: EventEmitter<string> = new EventEmitter<string>();
  @Input() set ngModel(value: string) {
      this.modelData = value;
      this.NgModuleChange.emit(this.modelData);
  }
  get ngModel() {
    return this.modelData;
  }
  // end model

  constructor() { }

  ngOnInit(): void {
    this.ngClass['col-' + this.col] = true;
  }

}
