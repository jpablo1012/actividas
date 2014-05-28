using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.UI.Xaml.Data;

namespace PlastiSoft_WP.Utils.Converts
{
    
    public class CreadoPor : IValueConverter
    {
        public object Convert(object value, System.Type type, object parameter, string language)
        {
            return "creado por: " + value.ToString();
        }

        public object ConvertBack(object value, System.Type type, object parameter, string language)
        {
            return value;
        }
    }

    public class NumeroPedido : IValueConverter
    {
        public object Convert(object value, System.Type type, object parameter, string language)
        {
            return "pedido n° " + value.ToString();
        }

        public object ConvertBack(object value, System.Type type, object parameter, string language)
        {
            return value;
        }
    }

    public class FechaCreacion : IValueConverter
    {
        public object Convert(object value, System.Type type, object parameter, string language)
        {
            DateTime tiempo;
            if (value is DateTime)
            {
                tiempo = (DateTime)value;
                return "creado el " + tiempo.ToString("dd/mm/yyyy");
            }
            else
                return value;
        }

        public object ConvertBack(object value, System.Type type, object parameter, string language)
        {
            return value;
        }
    }

    public class Estado : IValueConverter
    {
        public object Convert(object value, System.Type type, object parameter, string language)
        {
            return "estado: " + value.ToString();
        }

        public object ConvertBack(object value, System.Type type, object parameter, string language)
        {
            return value;
        }
    }
}
