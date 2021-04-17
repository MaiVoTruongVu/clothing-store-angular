import { Customer } from './customer';
export interface Bill {
    id: number;
    code: string;
    createdDate: Date;
    deliveryAddress: string;
    customer: Customer;
}
