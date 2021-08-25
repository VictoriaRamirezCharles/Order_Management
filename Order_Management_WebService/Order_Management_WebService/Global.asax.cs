using Order_Management_WebService.DataLayer;
using Order_Management_WebService.DataLayer.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;

namespace Order_Management_WebService
{
    public class WebApiApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            AreaRegistration.RegisterAllAreas();
            GlobalConfiguration.Configure(WebApiConfig.Register);
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);


            DatabaseAccess dataAccess = new DatabaseAccess();
            dataAccess.LoadConnection();
            Order_Management_Context Context = new Order_Management_Context();
            Context.Database.Initialize(false);
        }
    }
}
