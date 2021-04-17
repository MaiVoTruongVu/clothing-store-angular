import { Bill } from './bill';
import { Product } from './product';
export interface Detail {
    id: number;
    price: number;
    quantity: number;
    product: Product;
    bill: Bill;
}
