using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PlastiSoft_WP.Models
{
    public class Usuario
    {
        public int id { get; set; }
        public string nombre { get; set; }
        public string apellido { get; set; }
        public string tipo { get; set; }
        public string contraseña { get; set; }
        public string empleado_cedula { get; set; }
        public string cliente_cedula { get; set; }
        
    }
}
