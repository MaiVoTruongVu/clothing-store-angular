import { Product } from './product';
export interface Image {
    id: number;
    name: string;
    url: string;
    product: Product;
}
