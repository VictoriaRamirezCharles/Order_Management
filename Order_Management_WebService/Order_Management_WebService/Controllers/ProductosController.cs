﻿using Order_Management_WebService.DataLayer;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Order_Management_WebService.Controllers
{
    [RoutePrefix("productos")]
    public class ProductosController : BaseApiController
    {
        [HttpGet]
        [Route("get-all")]
        public IHttpActionResult GetallProducts()
        {
            return Response(() =>
            {
                var data = _Business.Productos.GetAll();
                return data;
            });
        }

        [HttpGet]
        [Route("get-by-id/{id}")]
        public IHttpActionResult GetProductById(int id)
        {
            return Response(() =>
            {
                var data = _Business.Productos.GetById(id);
                return data;
            });
        }

        [HttpPost]
        [Route("add")]
        public IHttpActionResult AddProduct([FromBody] E_Productos model)
        {
            return Response((WebResponse res) =>
            {
                var response = _Business.Productos.Add(model);

                if (response != null)
                {
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The product has been added successfully."
                    };
                }
                else
                {
                    res.Code = WebResponse.ResponseCode.error;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Error",
                        Body = $"There is an error."
                    };
                }

                return res;
            });
        }

        [HttpPut]
        [Route("update")]
        public IHttpActionResult UpdateProduct([FromBody] E_Productos model)
        {
            return Response((WebResponse res) =>
            {
                try
                {
                    _Business.Productos.Update(model);
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The product has been Updated successfully."
                    };

                }
                catch(Exception ex)
                {
                    res.Code = WebResponse.ResponseCode.error;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Error",
                        Body = $"There is an error.{ex.Message}"
                    };
                }

                return res;
            });
        }

        [HttpDelete]
        [Route("delete/{id}")]
        public IHttpActionResult DeleteProduct(int id)
        {
            return Response((WebResponse res) =>
            {
                
                    var result =_Business.Productos.DeleteById(id);
                    if (result)
                    {
                        res.Code = WebResponse.ResponseCode.success;
                        res.Message = new WebResponse.ResponseMessage
                        {
                            Title = "Success",
                            Body = "The product has been Updated successfully."
                        };
                    }
                    else
                    {
                        res.Code = WebResponse.ResponseCode.error;
                        res.Message = new WebResponse.ResponseMessage
                        {
                            Title = "Error",
                            Body = $"There is an error"
                        };
                    }
                  
                return res;
            });
        }
    }
}
