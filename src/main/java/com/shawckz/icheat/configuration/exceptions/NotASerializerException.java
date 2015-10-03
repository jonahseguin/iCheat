/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.configuration.exceptions;


public class NotASerializerException extends Exception {

    public NotASerializerException() {
        super( "Config given an object which doesn't extend serializer" );
    }
}
