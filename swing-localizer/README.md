# Swing localizer

Localization standard Java Swing components

## Using

- include library in your project
    - as [maven dependency](https://github.com/azzibom/swing-localizer/packages)
    - or other way
- add `swing_<lang>_<country>.properties` file with your localization in `resources` folder in your project.
    - or create your custom `BundleStore` with use [SPI](https://en.wikipedia.org/wiki/Service_provider_interface)
    - P.S. Russian localization added by default
- call static method `localize` from class `SwingLocalizer`.

  Examples:
  ```java
  import SwingLocalizer;
  public class Main {
    static {
      SwingLocalizer.localize();
    }
    public static void main(String[] args) {
      // ...
    }
  }
  ```
  or
  ```java
  import SwingLocalizer;
  public class Main {
    public static void main(String... args) {
      SwingLocalizer.localize();
      // ...
    }
  }
  ```

## Compile and packaging

Using `Maven`
in project root directory execute `mvn clean package`.
