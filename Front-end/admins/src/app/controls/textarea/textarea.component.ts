import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-textarea',
  templateUrl: './textarea.component.html',
  styleUrls: ['./textarea.component.css']
})
export class TextareaComponent implements OnInit {
  @Input() label: string;
  @Input() placeholder: string;
  @Input() type = 'text';
  @Input() name: string;
  @Input() rows: number;
  @Input() disabled: string;
  @Input() formControl: FormControl;
  modelData: string;
  @Output() NgModuleChange: EventEmitter<string> = new EventEmitter<string>();

  @Input() set ngModel(value: string) {
    this.modelData = value;
    this.NgModuleChange.emit(this.modelData);
  }
  get ngModel() {
    return this.modelData;
  }

  constructor() { }

  ngOnInit(): void {
  }

}
