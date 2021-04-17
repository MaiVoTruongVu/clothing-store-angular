import { Color } from './color';
import { Size } from './size';
import { Type } from './type';
import { Producer } from './producer';
export interface Product {
    id: number;
    code: string;
    avatar: string;
    name: string;
    price: number;
    description: string;
    isHot: boolean;
    gender: number;
    producer: Producer;
    type: Type;
    size: Size;
    color: Color;
}
