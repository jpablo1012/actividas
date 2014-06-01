using PlastiSoft_WP.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PlastiSoft_WP.Services
{
    public class Spedido
    {
        private string[] estado = {"pendiente", "rechazado", "en extrusíon", "en impresión", "en extrusión", "finalizado"};
        private int id = 1000;
        public List<Pedido> getPedidos(int count = 10)
        {
            var lista = new List<Pedido>();
            var random = new Random();
            for (int i = 0; i < count; i++)
            {
                if (id >= 0)
                {
                    var pedido = new Pedido();
                    pedido.numeroPedido = id;
                    pedido.cliente_cedula = (random.Next() * 999999999) + "";
                    pedido.fecha_creacion = RandomDay();
                    pedido.estado = estado[random.Next(estado.Length)];

                    lista.Add(pedido);

                    id--;
                }
            }

            return lista;
        }

        Random rnd = new Random();
        private DateTime RandomDay()
        {
            int year = rnd.Next(1900, 9999);
            int month = rnd.Next(1, 12);
            int day = DateTime.DaysInMonth(year, month);

            int Day = rnd.Next(1, day);

            DateTime dt = new DateTime(year, month, Day);
            return dt;
        }
    }
}
