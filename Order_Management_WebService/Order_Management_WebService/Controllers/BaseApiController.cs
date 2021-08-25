using Order_Management_WebService.BusinessLayer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Results;

namespace Order_Management_WebService.Controllers
{
    public class BaseApiController : ApiController
    {
        public BusinessModel _Business = new BusinessModel();

        OkNegotiatedContentResult<WebResponse> MainOk(WebResponse content)
        {
            var result = new WebResponse();

            result = content;

            return Ok(result);
        }

        public OkNegotiatedContentResult<WebResponse> Response(Func<WebResponse, WebResponse> content, Func<WebResponse, WebResponse> ErrorFunction = null)
        {
            var res = new WebResponse();
            try
            {
                res = content(res);
            }
            catch (Exception exception)
            {
                string message = exception.InnerException != null ? exception.InnerException.Message : exception.Message;
                res.Message = new WebResponse.ResponseMessage
                {
                    Title = "Error",
                    Body = message
                };
                res.Code = WebResponse.ResponseCode.error;
                if (ErrorFunction != null) { res = ErrorFunction(res); }
               
            }
            return MainOk(res);
        }

        public OkNegotiatedContentResult<WebResponse> Response<T>(Func<T> content, Func<T> ErrorFunction = null)
        {
            var res = new WebResponse();
            try
            {
                res.Data = content();
            }
            catch (Exception exception)
            {
                string message = exception.InnerException != null ? exception.InnerException.Message : exception.Message;
                res.Message = new WebResponse.ResponseMessage
                {
                    Title = "Error",
                    Body = message
                };
                res.Code = WebResponse.ResponseCode.error;
                if (ErrorFunction != null) { res.Data = ErrorFunction(); }
            
            }
            return MainOk(res);
        }
    }
}
