package com.example.demo.practice.factory;

import com.example.demo.exception.BusinessException;

public class ShapeFactory {
   public Shape getShape(String shape){
       switch (shape){
           case "CIRCLE":return new Circle();
           case "RECTANGLE":return new Rectangle();
           case "SQUARE":return new Square();
           default: throw new BusinessException("error shape");
       }
   }
}
