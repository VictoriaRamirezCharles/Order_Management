using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService
{
    public class WebResponse
    {
        public ResponseCode Code { get; set; } = ResponseCode.success;
        public ResponseMessage Message { get; set; }
        public object Data { get; set; }

        public enum ResponseCode
        {
            success = 1,
            warning = 2,
            error = 3,
            sessionExpired = 4
        }

        public class ResponseMessage
        {
            public ResponseMessage() { }
            public ResponseMessage(string message)
            {
                this.Body = message;
            }
            public string Title { get; set; }
            public string Body { get; set; }
        }
    }
}