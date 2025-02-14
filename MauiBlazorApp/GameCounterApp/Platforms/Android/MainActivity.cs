using Android.App;
using Android.Content.PM;
using Android.OS;

namespace GameCounterApp
{
    [Activity(
    //Theme = "@style/Maui.SplashTheme",
    Theme = "@style/Maui.MainTheme.NoActionBar",
    MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation | ConfigChanges.UiMode | ConfigChanges.ScreenLayout | ConfigChanges.SmallestScreenSize)]
    public class MainActivity : MauiAppCompatActivity
    {
    }
}