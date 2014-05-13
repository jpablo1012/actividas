using PlastiSoft_WP.Views.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Storage;
using Windows.Storage.Pickers;
using Windows.Storage.Streams;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Media.Imaging;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkID=390556

namespace PlastiSoft_WP.Views.Cliente
{
    /// <summary>
    /// Página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class CrearClienteView : Page, IFileOpenPickerContinuable
    {
        public CrearClienteView()
        {
            this.InitializeComponent();
        }

        /// <summary>
        /// Se invoca cuando esta página se va a mostrar en un objeto Frame.
        /// </summary>
        /// <param name="e">Event data that describes how this page was reached.
        /// This parameter is typically used to configure the page.</param>
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
        }

        private void imgCliente_Tapped(object sender, TappedRoutedEventArgs e)
        {
            //if (imgCliente.Source != null)
            //{

            //    Frame.Navigate(typeof(VerImagen), imgCliente.Source);
            //}
        }

        private void btnElegirImagen_Tapped(object sender, TappedRoutedEventArgs e)
        {
            var photo = new FileOpenPicker();
            photo.ViewMode = PickerViewMode.Thumbnail;
            photo.SuggestedStartLocation = PickerLocationId.PicturesLibrary;
            photo.FileTypeFilter.Add(".jpg");
            photo.FileTypeFilter.Add(".jpeg");
            photo.FileTypeFilter.Add(".png");
            photo.FileTypeFilter.Add(".bmp");

            photo.ContinuationData["Operation"] = "EstablecerImagenCliente";

            photo.PickSingleFileAndContinue();
        }

        private void btnQuitarImagen_Tapped(object sender, TappedRoutedEventArgs e)
        {
            imgCliente.Source = null;
        }

        public async void ContinueFileOpenPicker(Windows.ApplicationModel.Activation.FileOpenPickerContinuationEventArgs args)
        {
            if ((args.ContinuationData["Operation"] as string) == "EstablecerImagenCliente" && args.Files.Count > 0)
            {
                StorageFile file = args.Files[0];
                IRandomAccessStream foto = await file.OpenAsync(Windows.Storage.FileAccessMode.Read);
                BitmapImage bmp = new BitmapImage();

                bmp.SetSource(foto);
                imgCliente.Source = bmp;
            }
        }
    }
}
