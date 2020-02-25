

public aspect TraceAspect {
   pointcut classToTrace(): within(*App);
   pointcut methodToTrace():  classToTrace() &&  execution(String getName());

   before(): methodToTrace() {
      String info = thisJoinPointStaticPart.getSignature() + ", " //
            + thisJoinPointStaticPart.getSourceLocation().getFileName() + ", " //
            + thisJoinPointStaticPart.getSourceLocation().getLine();
      System.out.println("\t-->" + info);
   }

   after(): methodToTrace() {
      System.out.println("\t<--" + thisJoinPointStaticPart.getSignature());
   }
}
