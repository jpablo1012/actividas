using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PlastiSoft_WP.Models
{
    public class Pedido
    {
        public int numeroPedido { get; set; }
        public string cliente_cedula { get; set; }
        public int bolsa_referencia { get; set; }
        public float precio { get; set; }
        public DateTime fecha_entrega { get; set; }
        public string factura { get; set; }
        public string tipo_venta { get; set; }
        public DateTime fecha_creacion { get; set; }
        public string factura_despacho { get; set; }
        public string estado { get; set; }
        public float factor { get; set; }
        public float peso_millar { get; set; }
        public float basee {get; set;}
        public string referencia { get; set; }
        public float cantidad { get; set; }
        public string tipo_cantidad { get; set; }
        public bool extrusion { get; set; }
        public bool impresion { get; set; }
        public bool sellado { get; set; }
    }
}
