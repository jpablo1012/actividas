using System;
using System.Net;
using System.Collections.Generic;
using System.Linq;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Plastisoft
{
    public partial class MainPage : PhoneApplicationPage
    {
        // Constructor
        public MainPage()
        {
            InitializeComponent();

            // Establecer el contexto de datos del control ListBox control en los datos de ejemplo
            DataContext = App.ViewModel;
        }

        private void irA(object sender, System.Windows.Input.GestureEventArgs e)
        {
            var donde = (TextBlock)sender;
            var direccion = "MainPage.xaml";
            switch(donde.Name){
                case "btnCrearEmpleado":
                    direccion = "/Views/Empleado/crear.xaml";
                    break;
            }

            NavigationService.Navigate(new Uri(direccion, UriKind.Relative));
        }
    }
}