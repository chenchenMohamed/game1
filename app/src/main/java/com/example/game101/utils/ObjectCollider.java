package com.example.game101.utils;

import com.example.game101.objects.Rectangle;

public class ObjectCollider {
    public Rectangle rectangle;
    public int isVertical = 0;

    public ObjectCollider(Rectangle rectangle){
        this.rectangle = rectangle;
    }
}


