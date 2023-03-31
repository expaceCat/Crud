package org.expasecat.crudapp;

import org.expasecat.crudapp.dao.DevelopersDao;
import org.expasecat.crudapp.viewer.DeveloperView;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DeveloperView developerView = new DeveloperView();
        developerView.start();

    }
}
