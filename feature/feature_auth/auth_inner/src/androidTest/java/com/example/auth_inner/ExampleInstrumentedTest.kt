import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.auth_inner.ui.fragment.SingUpFragment
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val ruleInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun useAppContext() {

        val mockNavController = mockk<NavController>()

        every {
            mockNavController.navigate(any(), any(), any(), any())
        } just runs

        val scenario = launchFragmentMyScenario(null, SingUpFragment(), mockNavController)
//        scenario.close()
    }
}

inline fun <reified F : Fragment> launchFragmentMyScenario(
    bundle: Bundle?, fragment: F, navController: NavController
): FragmentScenario<F> {
    return launchFragmentInContainer(bundle, androidx.appcompat.R.style.Theme_AppCompat) {
        fragment.also { fragment ->
            fragment.viewLifecycleOwnerLiveData.observeForever { lifeCycleOwner ->
                if (lifeCycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }
    }
}
