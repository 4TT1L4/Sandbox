package WeakReference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/*
 * Sample application demonstrating the usage of WeakReferences and ReferencesQueues to detect Garbage Collection.
 */
public class WeakReferenceTest{
    
    ReferenceQueue<A> referenceQueue;
    
    /*
     * Example class with a single field.
     */
    class A
    {
        String value;
        
        public A(String value)
        {
            this.value= value;
        }
        
        String value()
        {
            return value;
        }
    }
    
    @Test
    public void test()
    {
        // The reference Queue to store the GCed references
        this.referenceQueue = new ReferenceQueue<A>();        
        
        // The only non-weak reference to the aInstance
        A aInstance = new A("Some value to be stored.");
        List<A> list = new ArrayList<A>();
        
        // Create a WeakReference with the aInstance and the referenceQueue.
        WeakReference<A> r = new WeakReference<A>(aInstance, this.referenceQueue);        
        
        Boolean hadBeenGabargeCollected;
        hadBeenGabargeCollected = testForGarbageCollection(this.referenceQueue.poll());
        
        // We still have the "aInstance" reference. Should not be Garbage Colleceted.
        Assert.assertFalse(hadBeenGabargeCollected);
        
        System.out.println("The value of the aInstence from the WeakReference: " + r.get().value());
        
        // Remove the only non-weak reference to the aInstance
        aInstance = null;
        System.gc();
        
        for(int i = 0; i < 5000; i++)
        {
            list.add(new A("A instance:" + i));
        }
        
        //The only non-weak reference had been removed. The first A instance should be already Garbage Collected.
        
        // 1.:
        // The get() method of the WeakReference returns null if the instance had been Garbage Collected.
        Assert.assertTrue(r.get() == null);
        
        // 2.:
        // We can also get the reference for the Garbage Collected instances from the ReferenceQueue.
        hadBeenGabargeCollected = testForGarbageCollection(this.referenceQueue.poll());
        Assert.assertTrue(hadBeenGabargeCollected);
    }

    /*
     * Test the passed reference if it refers to a Garbage Collected instance or not.
     */
    private Boolean testForGarbageCollection(Reference<?> reference) {
        Boolean hadBeenGabargeCollected;
        if( reference != null)
        {
            hadBeenGabargeCollected = reference.get() == null ? true : false;
            if(hadBeenGabargeCollected)
            {
                System.out.println("Yes - The A instance had been Garbage Collected.");                    
            }
            else
            {
                System.out.println("No, - The A instance is not yet Garbage Collected.");                
            }
        }
        else
        {
            hadBeenGabargeCollected = false;
            System.out.println("No - The A instance is not yet Garbage Colleceted, the reference is null.");            
        }
        
        return hadBeenGabargeCollected;
    }
}
